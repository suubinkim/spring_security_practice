import {useEffect, useState} from "react";
import fetchAuthorizedPage from "./fetchAuthorizedPage";
import {useNavigate} from "react-router-dom";

const Admin = () => {
    const navigate = useNavigate();
    const [data, setData] = useState("");
    useEffect(() => {
        fetchAuthorizedPage("http://localhost:8011/admin")
            .then(result => setData(result));
    }, []);
    return <div>
        {data && <h2>{data}</h2>}
        <button
            type="button"
            className="navyBtn"
            onClick={() => navigate('/')}
        >
            Home
        </button>
    </div>
}

export default Admin;
