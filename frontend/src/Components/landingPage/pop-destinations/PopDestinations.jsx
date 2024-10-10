import DestinationCart from "./destinationcart/DestinationCart";
import "./PopDestinations.scss";

export default function PopDestinations() {
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


    return (
        <section id="PopDestinations">
            <h3>Plan your perfect trip</h3>
            <p>Search Flights & Places Hire to our most popular destinations</p>
            <section id="DestinationsCategorys">
                {countries.map((destination, index) => (
                    <DestinationCart 
                        key={index} 
                        country={destination.country} 
                        img={destination.img} 
                    />
                ))}
            </section>
        </section>
    );
}