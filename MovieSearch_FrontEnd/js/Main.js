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
                        <a onclick="movieSelected('${movie.imdbID}')" class ="btn btn-primary" href ="#">View Details</a>
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
function movieSelected(id){
    sessionStorage.setItem('movieId', id);
    window.location='DetailPage.html';
    return false;
}
function getMovie(){
    let movieId = sessionStorage.getItem('movieId');
     axios.get('http://www.omdbapi.com/?i='+movieId+'&apikey=e9497f8')
        .then((response)=>{
            console.log(response);
            let movie = response.data;

            let output = `
                <div class = "row">
                    <div class = "col-md-4">
                        <img src = "${movie.Poster}" class = "thumbnail">
                    </div>
                    <div class = "col-md-8">
                        <h2>${movie.Title}</h2>
                        <ul class= "list-group">
                            <li class = "list-group-item"><strong>Genre:</strong> ${movie.Genre}</li>
                            <li class = "list-group-item"><strong>Released:</strong> ${movie.Released}</li>
                            <li class = "list-group-item"><strong>Rated:</strong> ${movie.Rated}</li>
                            <li class = "list-group-item"><strong>IMDB Rating:</strong> ${movie.imdbRating}</li>
                            <li class = "list-group-item"><strong>Director:</strong> ${movie.Director}</li>
                            <li class = "list-group-item"><strong>Writer:</strong> ${movie.Writer}</li>
                            <li class = "list-group-item"><strong>Actors:</strong> ${movie.Actors}</li>

                        </ul>
                    </div>
                </div>
                <div class ="row">
                    <div class ="well">
                        <h3>Plot</h3>
                        ${movie.Plot}
                        <hr>
                        <a href="http://www.imdb.com/title/${movie.imdbID}" target="_blank" class="btn btn-primary">Go to Website</a>
                        <a href="SearchPage.html" class="btn btn-default">Go back to Search</a>
            `;
            $('#movie').html(output);
        })
        .catch((err)=>{
            console.log(err);
        });

}