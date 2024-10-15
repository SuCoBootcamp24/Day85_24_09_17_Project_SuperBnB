import BtnLight from "../buttons/buttonLight/BtnLight";
import DestinationCart from "./destinationcart/DestinationCart";
import "./PopDestinations.scss";

export default function PopDestinations(props) {
  
    return (
        <section id="PopDestinations">
            <article className="titleBoxOne" >
                <div>
                    <h3>Plan your perfect trip</h3>
                    <p>Search Flights & Places Hire to our most popular destinations</p>
                </div>

                <BtnLight text={"See more places"} />

            </article>

            <article id="DestinationsCategorys">
                {props.countries.map((destination, index) => (
                    <DestinationCart 
                        key={index} 
                        country={destination.country} 
                        img={destination.img} 
                    />
                ))}
            </article>
        </section>
    );
}