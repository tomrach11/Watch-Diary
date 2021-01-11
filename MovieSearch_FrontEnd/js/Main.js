$(document).ready(()=>{
    $('#searchForm').on('submit', (e)=>{
        //console.log($('#searchText').val());
        let searchText = $('#searchText').val();
        getMovies(searchText);
        e.preventDefault();
    });
});

function getMovies(searchText){
    axios.get('http://www.omdbapi.com/?i=tt3896198&apikey=e9497f8'+searchText)
    .then((response)=>{
        console.log(response);
    })
    .catch((err)=>{
        console.log(err);
    });
}