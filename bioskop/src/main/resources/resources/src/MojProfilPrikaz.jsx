import React from "react"

function MojProfilPrikaz(props){
    return(
        <div style={{display: "inline-block"}} id="prikazProfila">
            <p>Korisnicko ime: {props.state.user.korisnickoIme} </p>
            <p>Datum registracije: {props.state.user.datumRegistracije} </p>
            <p>Tip korisnika: {props.state.user.authorities} </p>
            <input type="password" name="oldPassword" value={props.state.password} onChange={props.handleChange} placeholder="Stara lozinka..."/>
            <br/>
            <input type="password" name="newPassword" value={props.state.password} onChange={props.handleChange} placeholder="Nova lozinka..."/>
            <br/>
            <button onClick={props.handleClick}>Izmenite lozinku</button>
        </div>
    )
}

export default MojProfilPrikaz