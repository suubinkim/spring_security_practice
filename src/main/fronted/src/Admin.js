import {useEffect, useState} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import fetchAuthorizedPage from "./fetchAuthorizedPage";

const Admin = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [data, setData] = useState("");
    useEffect(() => {
        console.log("admin")

        fetchAuthorizedPage("http://localhost:8011/admin", navigate, location)
            .then(result => setData(result));
    }, [location, navigate]);
    return data && <h2>{data}</h2>;
}

export default Admin;
