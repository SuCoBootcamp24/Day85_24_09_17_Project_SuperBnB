import "./DestinationCart.scss";

export default function DestinationCart(props) {
   
    const imageSrc = `/img/countries/${props.img}`;

    return (
        <article className="DestiCarts">
            <div className="DestiImgBox">
                <img src={imageSrc} alt={props.country} />
            </div>

            <div className="DastiTextBox">
            <h4>{props.country}</h4>

            <p>BnBs &middot; Flights</p>
            </div>
        </article>
    );
}