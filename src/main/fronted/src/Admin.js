import {useEffect, useState} from "react";
import fetchAuthorizedPage from "./fetchAuthorizedPage";

const Admin = () => {
    const [data, setData] = useState("");
    useEffect(() => {
        fetchAuthorizedPage("http://localhost:8011/admin")
            .then(result => setData(result));
    }, []);
    return data && <h2>{data}</h2>;
}

export default Admin;
