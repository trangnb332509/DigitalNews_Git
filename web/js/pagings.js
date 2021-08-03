function renderPager(id, pageindex, total, gap, keyword)
{
    var head = "<a href='Search?pageindex=";
    var txtSearch = "&txtSearch="+ keyword + "'>";
    var container = document.getElementById(id);
    //display the first page
    if (pageindex > gap + 1) {
        container.innerHTML += head + 1 + txtSearch +"1</a>";
    }
    //display the page that in gap and before current page
    for (var i = pageindex - gap; i < pageindex; i++) {
        //pageindex must be greater than 0
        if (i >= 1) {
            container.innerHTML += head + i + txtSearch  + i + "</a>";
        }
    }
    //display the current page
    container.innerHTML += "<span>" + pageindex + "</span>";
    //display the page that in gap and after current page
    for (var i = pageindex + 1; i <= pageindex + gap; i++) {
        //pageindex must be less than or equal to total
        if (i <= total) {
            container.innerHTML += head + i + txtSearch + i + "</a>";
        }
    }
    //display the last page
    if (pageindex < total - gap) {
        container.innerHTML += head + total + txtSearch + total + "</a>";
    }
}





