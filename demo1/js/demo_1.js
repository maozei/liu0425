/**
 * Created by liu on 2017/7/15.
 */
window.onload=function () {
    var _div=document.querySelector(".wrap");
    var _ol_li=_div.querySelectorAll("ol>li");
    var _ul_li=_div.querySelectorAll("ul>li");
    for(var i=0;i<_ol_li.length;i++){
        _ol_li[i].index=i;
        _ul_li[i].index=i;
        _ol_li[i].onmouseover=function () {
            var thiz=this;
            for(var i=0;i<_ul_li.length;i++){
                _ol_li[i].classList.remove("active");
                _ul_li[i].style.filter="alpha(opacity=0)";
                _ul_li[i].style.opacity=0;
            }
            thiz.classList.add("active");
            // _ul_li[this.index].style.display="block";
            // _ul_li[thiz.index].style.transition="opacity 1s ease-out";
            _ul_li[thiz.index].style.opacity=1;
            _ul_li[thiz.index].style.filter="alpha(opacity=100)";
            // setTimeout(function () {
            // },101);
            // _ul_li[this.index].style.transition="opacity 10s";
            // _ul_li[this.index].style.opacity=100;
        }

    }
}


