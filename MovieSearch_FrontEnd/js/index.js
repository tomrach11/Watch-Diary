$(document).ready(function ()
{
    addListItem();
    addListItem();
    addListItem();
    addListItem();
    addListItem();
});

function addListItem()
{
    var node = document.createElement("li");
    var textNode = document.createTextNode("Movie 4");
    node.appendChild(textNode);
    document.getElementById("list").appendChild(node);
}