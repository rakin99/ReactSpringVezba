import React from "react"

function PrikazSvihKorisnika(props){
    return(
            <tr>
                <td><button style={{color: "blue"}} onClick={props.handleClick} name={props.user.korisnickoIme}>{props.user.korisnickoIme}</button></td>
                <td>{props.user.datumRegistracije}</td>
                <td style={{textAlign: "center"}}>{props.user.authorities}</td>
            </tr>
    )
}

export default PrikazSvihKorisnika