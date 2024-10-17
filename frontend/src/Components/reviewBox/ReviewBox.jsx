
import BtnLight from "../buttons/buttonLight/BtnLight";
import "./ReviewBox.scss";
import ReviewCard from "./reviewCard/ReviewCard";

export default function ReviewBox() {


    return (
        <section id="reviewBox">
            <article className="titleBoxOne">
                <div>
                    <h3>Plan your perfect trip</h3>
                    <p>Search Flights & Places Hire to our most popular destinations</p>
                </div>

                <BtnLight text={"See All"} />

            </article>

            <article id="reviewCards">
               <ReviewCard />

            </article>
        </section>
    );
}
