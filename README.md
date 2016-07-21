
![Jkidney](http://img.blog.csdn.net/20160711131314101)

Jkidney的色系采用蓝色，简洁明了，看起来也比较舒服，让人感觉很安心。

### 2016年7月8号更新


从四月份开始立意到现在的7月份，整整三个月左右时间，Jkidney第一版发布了，不过这期间真正写代码的时间不长，大概只有半个月左右，而且都是利用业余时间来完成的，比如晚上或者周末等，其余的时间就在构思如何设计app，如何设计后台表结构之类的，这倒是很费时间。不过好在最终完成了肾管家第一版的开发工作，下面来简单看下一下最终的实现效果，从设计到开发全是我一人搞定的，所以肯定会有很多不足的地方，希望可以提出来，让我把它做的越来越好！
### 肾管家Jkidney V2.0
#### 完成的功能主要有：

 1. UI大调整，清爽、干净
 2. 新增发帖、评贴功能
 3. 修复已知bug

### 肾管家Jkidney V1.0
#### 完成的功能主要有：
1. 登录/注册功能
1. 健康资讯阅读，查看一些关于健康的新闻以及一些常识
1. 检查化验单的备份以及分析功能，化验报告单主要包含“肝功能，肾功能，尿蛋白，血压，体重，血糖”等化验。
1. 实现了一个简单的记事本功能，可以记录一些心得体会等。
1. 实现了一个小计算器功能，计算肌酐清除率和肾小球过滤率计算，可以大概的了解一下肾病患者目前所处的基本阶段。


### 效果实现图
![](http://img.blog.csdn.net/20160721095006244)

![](http://img.blog.csdn.net/20160721095016361)

![](http://img.blog.csdn.net/20160721095035947)

![](http://img.blog.csdn.net/20160721095048502)

![](http://img.blog.csdn.net/20160721095057932)




### 原理分析

在V1.0版本中主要用到了以下知识点，首先最重要的就是app中数据从哪里来以及我们如何存储数据，存储到哪里？我想这是大家所最想了解的，的确，作为一名android  App开发者，大伙很少有移动端和后台都精通的人，我也不例外，这也是我的弱点，所以我就网上查了一下，发现有隔后台一体实现的云端服务[Bmob](http://www.bmob.cn//)，通过Bmob我们就可以不用关心后端如何实现了，只需要设计一些基本的表结构即可使用了，很方便！

#### 后台数据存储 [Bmob](http://www.bmob.cn//)
支持android ios 等平台的开发，方便简单好学，省去了开发者很大的成本。

#### 资讯信息获取 [apiStore](http://apistore.baidu.com/)
在肾管家V1.0版本中实现了资讯的获取，本来一开始准本自己用爬虫来获取信息的，偶然在知乎里面看到百度有[apiStore](http://apistore.baidu.com/)的网站，里面集成了很多api 供开发者调用，简单方便，开发者只需要解析一下返回的结果就行了，简直神了。

#### 数据解析 [fastJson](https://github.com/alibaba/fastjson)

在资讯信息查看页面，需要解析后台服务器返回的数据，所以采用fastjson来解析，效率比较高，我们公司实际的项目就是用fastjson来解析的。

#### 数据刷新及分页加载
在列表页面需要用到数据刷新和分页加载的功能，我直接使用了RecyclerView来做的，但是RecyclerView不支持刷新及分页，这就需要我们自己来实现了，我们使用google提供的SwipeRefreshLayout来实现列表的刷新功能，使用
[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)来实现列表RecyclerView的分页加载功能。


#### 图片加载 [universal-image-loader](https://github.com/nostra13/Android-Universal-Image-Loader)

资讯列表页面的图片加载使用的是universal-image-loader

#### ViewPager及指示器 [pagerslidingtabstrip](https://github.com/astuetz/PagerSlidingTabStrip)
 
 在资讯分类的列表页面使用到了viewpager的指示器功能
 
#### app底部tab [ahbottomnavigation](https://github.com/aurelhubert/ahbottomnavigation)

在首页中，使用 ahbottomnavigation实现了“资讯，检查，我的”三个tab功能。

#### 柱状图分析 [hellocharts](https://github.com/lecho/hellocharts-android)
在检查化验单的列表页面，我们使用了hellocharts控件实现了所有检查的柱状图表示，可以明确的看出来变化规律。

基本上就是这些开源控件啦！


### apk下载地址
http://fir.im/7e4y?release_id=57902ab5548b7a5a83000017




### 关于我
[我的博客 http://crazyandcoder.github.io/about/](http://crazyandcoder.github.io/about/)

[我的github https://github.com/crazyandcoder](https://github.com/crazyandcoder)



----------
![http://crazyandcoder.github.io/](http://img.blog.csdn.net/20160325102501040)

