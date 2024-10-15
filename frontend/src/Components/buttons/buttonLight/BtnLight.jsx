import "./BtnLight.scss";

export default function BtnLight(props) {

    return(
        <button onClick={props.action}>
            {props.text}
        </button>
    )
}