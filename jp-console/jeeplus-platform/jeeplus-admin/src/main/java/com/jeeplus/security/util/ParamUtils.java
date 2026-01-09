package com.jeeplus.security.util;

import org.springframework.web.bind.annotation.RequestBody;

public class ParamUtils {

    /**
     * @description sql参数匹配效验
     */
    public static boolean sqlValidate(String str){

        // 统一转为小写
        String s = str.toLowerCase();
        // 过滤掉的sql关键字，特殊字符前面需要加\\进行转义
        String badStr =
                ".*select.*|.*update.*|.*and.*|.*or.*|.*delete.*|.*insert.*|.*truncate.*|.*char.*|.*into.*|.*substr.*|.*ascii.*|.*declare.*|.*exec.*|.*count.*|.*master.*|.*into.*|.*drop.*|.*execute.*|.*table.*|.*"+ "char.*|.*declare.*|.*sitename.*|.*xp_cmdshell.*|.*like.*|.*from.*|.*grant.*|.*use.*|.*group_concat.*|.*column_name.*|.*" +
                        "information_schema.columns.*|.*table_schema.*|.*union.*|.*where.*|.*order.*|.*by.*|.*sleep.*|.*database.*|.*length.*|.*" +
                        "'\\*.*|.*\\;.*|.*\\-.*|.*\\--.*|.*\\+.*|.*\\,.*|.*\\//.*|.*\\/.*|.*\\%.*|.*\\#.*";
        badStr+="javascript.*|.*script.*|.*sleep.*|.*sitename.*|.*prompt.*|.*img.*|.*alert.*|.*fscommand.*|.*onabort.*|.*onactivate.*|.*onafterprint.*|.*onafterupdate.*|.*onbeforeactivate.*|.*onbeforecopy.*|.*onbeforecut.*|.*onbeforedeactivate.*|.*onbeforeeditfocus.*|.*onbeforepaste.*|.*onbeforeprint.*|.*onbeforeunload.*|.*onbeforeupdate.*|.*onbegin.*|.*onblur.*|.*onbounce.*|.*oncellchange.*|.*onchange.*|.*onclick.*|.*oncontextmenu.*|.*oncontrolselect.*|.*oncopy.*|.*oncut.*|.*ondataavailable.*|.*ondatasetchanged.*|.*ondatasetcomplete.*|.*ondblclick.*|.*ondeactivate.*|.*ondrag.*|.*ondragend.*|.*ondragleave.*|.*ondragenter.*|.*ondragover.*|.*ondragdrop.*|.*ondragstart.*|.*ondrop.*|.*onended.*|.*onerror.*|.*onerrorupdate.*|.*onfilterchange.*|.*onfinish.*|.*onfocus.*|.*onfocusin.*|.*onfocusout.*|.*onhashchange.*|.*onhelp.*|.*oninput.*|.*onkeydown.*|.*onkeypress.*|.*onkeyup.*|.*onlayoutcomplete.*|.*onlosecapture.*|.*onload.*|.*onmediacomplete.*|.*onmediaerror.*|.*onmessage.*|.*onmousedown.*|.*onmouseenter.*|.*onmouseleave.*|.*onmousemove.*|.*onmouseout.*|.*onmouseover.*|.*onmouseup.*|.*onmousewheel.*|.*onmove.*|.*onmoveend.*|.*onmovestart.*|.*onoffline.*|.*ononline.*|.*onoutofsync.*|.*onpaste.*|.*onpause.*|.*onpopstate.*|.*onprogress.*|.*onpropertychange.*|.*onreadystatechange.*|.*onredo.*|.*onrepeat.*|.*onreset.*|.*onresize.*|.*onresizeend.*|.*onresizestart.*|.*onresume.*|.*onreverse.*|.*onrowsenter.*|.*onrowexit.*|.*onrowdelete.*|.*onrowinserted.*|.*onscroll.*|.*onseek.*|.*onselect.*|.*onselectionchange.*|.*onselectstart.*|.*onstart.*|.*onstop.*|.*onstorage.*|.*onsyncrestored.*|.*onsubmit.*|.*ontimeerror.*|.*ontrackchange.*|.*onundo.*|.*onunload.*|.*onurlflip.*|.*seeksegmenttime.*|.*javascript:";
        //使用正则表达式进行匹配
        boolean matches = s.matches(badStr);
        return matches;
    }


    public  static  boolean filePathValidate(String value) {

        //字符串转小写
        String s = value.toLowerCase();
        String badStr =".*\\.\\..*|.*c\\:.*|.*d\\:.*|.*e\\:.*|.*\\:.*|.*\\%3a.*|.*\\%2e.*|.*\\%3d.*|.*=.*|.*\\%.*";
        //使用正则表达式进行匹配
        boolean matches = s.matches(badStr);
        return matches;
    }

    public  static  String cleanXSS(String value) {
        value = value.replaceAll("[`~!@#$%^&*\\+\\=\\{}:\"?><【】]", "");
        value = value.replaceAll("<script[^>]*?>[\\s\\S]*?<\\/script>", "");
        value = value.replaceAll("<style[^>]*?>[\\s\\S]*?<\\/style>", "");
        value = value.replaceAll("<[^>]+>", "");
        value = value.replaceAll("\\s*|\t|\r|\n", "");
        value = value.replaceAll("<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>", "");
        value = value.replaceAll("%3C", "").replaceAll("%3E", "");
        value = value.replaceAll("\\(", "").replaceAll("\\)", "");
        value = value.replaceAll("%28", "").replaceAll("%29", "");
        value = value.replaceAll("'", "");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "");
        value = value.replaceAll("script", "");
        value = value.replaceAll("(\\b(concat|order|update|and|delete|insert|truncate|char|into|substr|ascii|declare|exec|count|into|alter|drop|execute|table|information_schema.columns|table_schema|union|where|by|char|declare|sitename|like|alert|grant|group_concat|column_name|refresh|floor|extractvalue|updatexml|exp|join|name_const|geometrycollection|polygon|multipoint|multlinestring|multpolygon|linestring:)\\b)", "");
        value = value.replaceAll("(\\b(javascript|script|sleep|sitename|prompt|img|alert|fscommand|onabort|onactivate|onafterprint|onafterupdate|onbeforeactivate|onbeforecopy|onbeforecut|onbeforedeactivate|onbeforeeditfocus|onbeforepaste|onbeforeprint|onbeforeunload|onbeforeupdate|onbegin|onblur|onbounce|oncellchange|onchange|onclick|oncontextmenu|oncontrolselect|oncopy|oncut|ondataavailable|ondatasetchanged|ondatasetcomplete|ondblclick|ondeactivate|ondrag|ondragend|ondragleave|ondragenter|ondragover|ondragdrop|ondragstart|ondrop|onended|onerror|onerrorupdate|onfilterchange|onfinish|onfocus|onfocusin|onfocusout|onhashchange|onhelp|oninput|onkeydown|onkeypress|onkeyup|onlayoutcomplete|onlosecapture|onload|onmediacomplete|onmediaerror|onmessage|onmousedown|onmouseenter|onmouseleave|onmousemove|onmouseout|onmouseover|onmouseup|onmousewheel|onmove|onmoveend|onmovestart|onoffline|ononline|onoutofsync|onpaste|onpause|onpopstate|onprogress|onpropertychange|onreadystatechange|onredo|onrepeat|onreset|onresize|onresizeend|onresizestart|onresume|onreverse|onrowsenter|onrowexit|onrowdelete|onrowinserted|onscroll|onseek|onselect|onselectionchange|onselectstart|onstart|onstop|onstorage|onsyncrestored|onsubmit|ontimeerror|ontrackchange|onundo|onunload|onurlflip|seeksegmenttime|javascript:)\\b)", "");
        return value;
    }

//    public static void main(String[] args) {
//        String aa = "userdir/2023/6/";
//        System.out.println(filePathValidate(aa));
//    }



}
