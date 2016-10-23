# android-formlayout-and-input-field
一个安卓form表单布局库，简化Android表单布局，带InputFieldView支持大部分表单场景。

主要功能集中在InputField控件中，该控件继承自EditText，拥有所有EditText的功能，同时添加了title标题字段，标题可以自定义位置，添加了头和尾部的图标添加，添加了清空功能，添加了验证功能，验证为空盒根据正则表达式验证。可以直接在xml中指定该EditText需要的正则表达式。，FormLayout控件继承自LinearLayout，主要功能是在FormLayout中定义属性，可以同时在FormLayout的类型为InputField的子 view生效。

主要属性：
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <!--input field的扩展属性-->
    <declare-styleable name="InputField">
        <!--title 可以显示在edit的左侧或上侧-->
        <attr name="titleText" format="string" />
        <!--titile颜色-->
        <attr name="titleTextColor" format="color" />
        <!--title尺寸-->
        <attr name="titleTextSize" format="dimension" />
        <!--把EditText划分为2分 一块区域为title 一块区域为EditText  该属性为title的宽度，左侧title距离编辑区域的距离， title在上方时该属性无效-->
        <attr name="titleAreaWidth" format="dimension" />
        <!-- 编辑区域的左侧图标-->
        <attr name="startDrawable" format="reference" />
        <!-- 编辑区域的尾部图标-->
        <attr name="endDrawable" format="reference" />
        <!-- 清空按钮图标-->
        <attr name="clearDrawable" format="reference" />
        <!-- 验证错误提示图标-->
        <attr name="wrongDrawable" format="reference" />
        <!-- 左侧title距离编辑区域的距离， title在上方时该属性无效-->
        <attr name="titleToInputSpace" format="dimension" />
        <!--编辑区域最多可能出现2个图标，一个时清空按钮，一个时自定义的图标，这个时俩个图标的距离-->
        <attr name="rightDrawableToDrawableSpace" format="dimension" />
        <!--title 可以定义自己的图标 这个时title和title图标的距离-->
        <attr name="titleToTitleDrawableSpace" format="dimension" />
        <!--title 还可以设置一个校的budget 这个时title和budget的距离-->
        <attr name="titleToTitleTagDrawableSpace" format="dimension" />
        <!--编辑区域的背景 如果设置titleType为outside 背景只设置给可编辑区域，否则背景会包括title在内-->
        <attr name="android:background" />
        <attr name="android:padding" />
        <!--设置开启不允许为空验证-->
        <attr name="validateNotNull" format="boolean" />
        <!--设置为空验证未通过的错误提示信息-->
        <attr name="nullErrorMessage" format="string" />
        <!--设置正则表达式验证-->
        <attr name="validateRegexString" format="string" />
        <!--正则表达式验证未通过的提示信息-->
        <attr name="validateErrorMessage" format="string" />
        <!--右侧图标的显示模式  只显示清空按钮和错误按钮，或者无等-->
        <attr name="rightDrawableType" format="enum">
            <enum name="showClearAndErrorTag" value="1" />
            <enum name="showErrorTag" value="2" />
            <enum name="showClearTag" value="3" />
            <enum name="none" value="4" />
        </attr>
        <!--标题的位置-->
        <attr name="titlePosition" format="enum">
            <enum name="topLeft" value="1" />
            <enum name="topCenter" value="2" />
            <enum name="topRight" value="3" />
            <enum name="centerLeft" value="4" />
            <enum name="center" value="5" />
            <enum name="centerRight" value="6" />
            <enum name="bottomLeft" value="7" />
            <enum name="bottomCenter" value="8" />
            <enum name="bottomRight" value="9" />
        </attr>
        <!--标题类型-->
        <attr name="titleType" format="enum">
            <enum name="innerLeft" value="1" />
            <enum name="outsideLeft" value="2" />
            <enum name="outsideTop" value="3" />
            <enum name="innerTop" value="4" />
        </attr>
        <!--title图标-->
        <attr name="titleDrawable" format="reference" />
        <!--title 的budget图标-->
        <attr name="titleTagDrawable" format="reference" />
        <!--title 图标位置-->
        <attr name="titleDrawablePosition" format="enum">
            <enum name="left" value="1" />
            <enum name="right" value="2" />
        </attr>
        <!--title 的budget图标位置-->
        <attr name="titleTagDrawablePosition" format="enum">
            <enum name="leftTop" value="1" />
            <enum name="rightTop" value="2" />
        </attr>

    </declare-styleable>

    <declare-styleable name="FormLayout">
        <attr name="inputFieldBackground" format="reference" />
        <attr name="inputFieldPadding" format="dimension" />
    </declare-styleable>

</resources>
```

上面的属性在FormLayout中设置，即可对FormLayout中所有子view生效，如果在子view中已有配置，则优先使用子view的。


感兴趣可以下载代码,运行sample查看详细信息,欢迎star和pr;

### License
   
MIT: http://mit-license.org/
