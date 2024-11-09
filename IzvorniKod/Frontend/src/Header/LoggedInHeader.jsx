import { Link } from "react-router-dom";
import SearchBar from "./SearchBar";

export default function LoggedInHeader() {
  return (
    <div className="header">
      <div className="logo-container">
        <Link to="/">
          <img className="logo" src="../public/OrmarkoLogo.png" alt="Logo" />
        </Link>
      </div>
      <SearchBar></SearchBar>
      <div className="headerbtndiv">
        <Link to="/profile" key={index}>
          <button className="headerbtn">Moj Profil</button>
        </Link>
      </div>
    </div>
  );
}
