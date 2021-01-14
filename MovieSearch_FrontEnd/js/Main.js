$(document).ready(()=>{
    $('#searchForm').on('submit', (e)=>{
        //console.log($('#searchText').val());

        let searchText = $('#searchText').val();
        getMovies(searchText);
        e.preventDefault();

    });
});

function getMovies(searchText){
   // var apikey ="e9497f8"
    axios.get('http://www.omdbapi.com/?s='+searchText+'&apikey=e9497f8')
    .then((response)=>{
        console.log(response);
        //put in an array of search movies
        let movies = response.data.Search;
        let output = '';
        $.each(movies,(index,movie)=>{
            output += `
                <div class = "col-md-3">
                    <div class="well text-center">
                        <img src = "${movie.Poster}">
                        <h5>${movie.Title}</h5>
                        <a onclick="movieSelected('${movie.imdbID}')" class ="btn btn=primary" href ="#">Movie details</a>
                    </div>
                </div>
            `;
        });

        $('#movies').html(output);

    })
    .catch((err)=>{
        console.log(err);
    });
}