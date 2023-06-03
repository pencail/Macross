package com.macross.server.Controller;


import com.alibaba.fastjson2.JSON;
import com.macross.server.Entity.RSS;
import com.macross.server.Entity.Setting;
import com.macross.server.Service.RssService;
import com.macross.server.Service.SettingService;
import com.macross.server.Utils.RssUtil;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.List;


@Controller
public class RssController {

    @Resource
    private RssService rssService;

    /**
     * 进入RSS页面
     *
     * @return
     */
    @RequestMapping("/RSS")
    public String RSS() {
        return "RSS/RSS";
    }

    /**
     * 需要格式化成layui能识别的json格式
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/RssLink")
    @ResponseBody
    public String RssLink() {

        List<RSS> rss = rssService.getRSS();
        String o = JSON.toJSONString(rss);

        return o;
    }

    /**
     * 跳转到搜索页面
     *
     * @return
     */
    @RequestMapping("/search")
    public String Search() {
        return "RSS/search";
    }

    /**
     * 搜索种子链接
     *
     * @param url
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/RssSearch")
    public String RssSearch(@RequestParam("rsslink") String url,
                            Model model) throws Exception {


        SyndFeed feed = RssUtil.RssSearch(url);

        //标题
        String title = feed.getTitle();

        model.addAttribute("title", title);

        List<SyndEntry> entry = feed.getEntries();

        model.addAttribute("entry", entry);
        return "RSS/list";
    }

    /**
     * 保存RSS链接并判断是否存在
     *
     * @param url
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("/SaveRss")
    @ResponseBody
    public String SaveRss(@RequestParam("rsslink") String url,
                          @RequestParam("rssname") String name) throws Exception {

        List<RSS> rs = rssService.selectRssByUrl(url);

//        判断是否已经存在订阅链接
        if ((rs != null && !rs.isEmpty()) || rs.size() > 0) {
            return "repeat";
        } else {
            RSS rss = new RSS();

            SyndFeed feed = RssUtil.RssSearch(url);

            String title = feed.getTitle();

            rss.setRssName(name);
            rss.setRssTitle(title);
            rss.setRssLink(url);

            boolean res = rssService.insertRss(rss);

            if (res) {
                return "true";
            }
            return "false";
        }
    }

    /**
     * 单个删除
     *
     * @param rssId
     */
    @RequestMapping("/deleteRSS")
    @ResponseBody
    public String deleteRSS(@RequestParam("rssId") int rssId) {
        int res = rssService.deleteById(rssId);
        if (res > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 批量删除
     * 前端传入要删除的id数组
     *
     * @return
     */
    @RequestMapping("/deleteRSSAll")
    @ResponseBody
    public String deleteRSSAll(@RequestParam("id[]") int[] id) {

        int res = 0;
        //遍历数组
        for (int i = 0; i < id.length; i++) {
//            System.out.println(id[i]);
            res = rssService.deleteById(id[i]);

            //删除完毕或者发生错误，跳出循环
            if (res == 0) {
                break;
            }
        }
        if (res > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 下载BT种子到指定文件夹
     *
     * @param BTurl
     * @param BTname
     * @return
     * @throws Exception
     */
    @RequestMapping("/RssDownload")
    @ResponseBody
    public String RssDownload(@RequestParam("BTUrl") String BTurl,
                              @RequestParam("BTName") String BTname) throws Exception {

        URL url = new URL(BTurl);
        String dir = rssService.download();
        File file = new File(dir);

        //file.exists 为true,则用！ 跳过方法
        if (!file.exists()) {
            file.mkdirs();
        }
        FileUtils.copyURLToFile(url, new File(dir + BTname));

        return "success";
    }

    /**
     * 设置自动下载
     *
     * @param model
     * @return
     */
    @RequestMapping("/Setting")
    public String upodataSetting(Model model) {
        String autodown = rssService.autoDownload();
        String dir = rssService.download();
        if ("true".equals(autodown)) {
            model.addAttribute("autodown", true);
        } else {
            model.addAttribute("autodown", false);
        }
        model.addAttribute("dir", dir);
        return "RSS/RssSetting";
    }

    /**
     * 设置RSS相关
     *
     * @return
     */
    @RequestMapping("/updataSetting/RSSsetting")
    @ResponseBody
    public String upodataSettings(@RequestParam("Dir") String Dir,
                                  @RequestParam("auto") String auto) {

        return rssService.updateSetting(Dir, auto);
    }
}
