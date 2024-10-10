import { Outlet } from "react-router-dom";
import Navigation from "../Components/navigation/Navigation";

export default function LandingPage() {
    return (
        <>
        <header>
            <Navigation />
        </header>
        <main>
            <Outlet />
        </main>
        </>
    )
}