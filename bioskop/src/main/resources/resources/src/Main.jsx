import React from "react"

import Filmovi from "./Filmovi"
import NoviFilm from "./NoviFilm"
import Users from "./Users"
import NovaProjekcija from "./NovaProjekcija"
import Projekcije from "./Projekcije"
import MojProfil from "./MojProfil"
import Izvestaji from "./Izvestaji"

function Main(props){

    const noviFilm=props.state.NoviFilm === true && <NoviFilm handleToUpdateAdd={props.handleToUpdateAdd} filmovi={props.state.filmovi}/>
    const users=props.state.Users === true && <Users users={props.state.Users} />
    const projekcije=props.state.idProjekcije === true && <Projekcije handleToUpdateProjekcije={props.handleToUpdateProjekcije} state={props.state}/>
    const novaProjekcija=props.state.NovaProjekcija === true && <NovaProjekcija setFalse={props.setFalse} setTrue={props.setTrue} handleToUpdateAddProjekcije={props.handleToUpdateAddProjekcije} state={props.state}/>
    const mojProfil=props.state.mojProfil === true && <MojProfil state={props.state}/>
    const izvestaji=props.state.izvestaji === true && <Izvestaji />
    return(
        <div id="main">
            <Filmovi handleToUpdateFilmovi={props.handleToUpdateFilmovi} state={props.state} />
            {noviFilm}
            {users}
            {projekcije}
            {novaProjekcija}
            {mojProfil}
            {izvestaji}
        </div>
    )
}

export default Main