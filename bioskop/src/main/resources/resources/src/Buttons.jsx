import React from "react"

function Buttons(props){

    const btnNoviFilm = props.state.roles.includes("ROLE_ADMIN") && <button 
                                                                    id="noviFilm" 
                                                                    name="NoviFilm" 
                                                                    onClick={props.handleClick}>
                                                                        Novi film
                                                                </button>

    const btnSviKorisnici = props.state.roles.includes("ROLE_ADMIN") && <button 
                                                                        id="sviKorisnici" 
                                                                        name="Users" 
                                                                        onClick={props.handleClick}>
                                                                            Svi korisnici
                                                                    </button>

    const btnLogout = props.state.logdeUsername !== "" &&  <button 
                                                                name="logout" 
                                                                onClick={props.handleClick}>
                                                                    Odjavi se
                                                            </button>

    const btnProjekcije = props.state.logdeUsername !== "" &&  <button 
                                                                name="idProjekcije"
                                                                onClick={props.handleClick}>
                                                                    Sve projekcije
                                                            </button>

    const btnNovaProjekcija = props.state.roles.includes("ROLE_ADMIN") &&  <button 
                                                                                name="NovaProjekcija" 
                                                                                onClick={props.handleClick}>
                                                                                Nova Projekcija
                                                                            </button>

    const btnMojProfil = props.state.roles.includes("ROLE_USER") &&  <button 
                                                                                name="mojProfil" 
                                                                                onClick={props.handleClick}>
                                                                                Moj profil
                                                                            </button>

    const btnIzvestaji = props.state.roles.includes("ROLE_ADMIN") &&  <button 
                                                                name="izvestaji" 
                                                                onClick={props.handleClick}>
                                                                    Izvestaji
                                                            </button>

    return(
        <div id="buttons">
            {btnNoviFilm}
            {btnSviKorisnici}
            <button id="btnFilmovi" name="Filmovi" onClick={props.handleClick}>Svi filmovi</button>
            {btnProjekcije}
            {btnNovaProjekcija}
            {btnIzvestaji}
            {btnMojProfil}
            <button name="logout" onClick={props.handleClick}>Odjavi se</button>
        </div>
    )
}

export default Buttons