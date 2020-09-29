import React from "react"

function Korisnik(props){
    console.log(JSON.stringify(props))
    return(
        <div style={{display: "inline-block"}} id="idKorisnik">
            <p>Korisnicko ime: {props.state.user.korisnickoIme}</p>
            <p>Datum registracije: {props.state.user.datumRegistracije}</p>
            <p>Uloga: {props.state.user.authorities}</p>
            <label>
            Admin:
                    <input 
                        onChange={props.handleChange} 
                        type="checkbox" 
                        name="admin"
                        checked={props.state.admin}/>
            </label>
            <br/>
            <button name={props.state.user.korisnickoIme} onClick={props.deleteUser}>Obrisi korisnika</button>
            <button onClick={props.cancel}>Tabela</button>
        </div>
    )
}

export default Korisnik