# YcherDialog

## 概述

仿 iOS UIAlertView 样式自定义 Dialog。

## 使用

Step 1. 将 JitPack 存储库添加到 build 文件中

将其添加到您的根目录 build.gradle 中，并放置在存储库的末尾:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. 添加依赖项

```
dependencies {
    implementation 'com.github.taodaren:YcherDialog:v1.0.0'
}
```

## 效果

![image](http://plvrjsntv.bkt.clouddn.com/github/images/ydialog/standard.png)
![image](http://plvrjsntv.bkt.clouddn.com/github/images/ydialog/no_btn.png)
![image](http://plvrjsntv.bkt.clouddn.com/github/images/ydialog/show_one.png)