import PopDestinations from "../Components/pop-destinations/PopDestinations";
import ReviewBox from "../Components/reviewBox/ReviewBox";

export default function LandingPage() {

    const countries = [
        {country:"Vimmerby, Sweden", img:"sweden.jpg"},
        {country:"Sydney, Australia", img:"sydney.png"},
        {country:"Baku, Azerbaijan", img:"baku.png"},
        {country:"Malé, Maldives", img:"maldives.png"},
        {country:"Paris, France", img:"paris.png"},
        {country:"New York, USA", img:"newyork.png"},
        {country:"London, UK", img:"london.png"},
        {country:"Tokjo, Japan", img:"japan.png"},
        {country:"Reykjavík, Iceland", img:"iceland.jpg"}
    ]

return(
    <main>
        <h1>Landing Page</h1>
        <PopDestinations countries={countries} />
        <ReviewBox />
        
    </main>

);
}