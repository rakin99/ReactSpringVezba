import React from "react"

function PrikazProjekcija(props){

    return(
        <tr>
            <td><button style={{color: "blue"}} onClick={props.handleClickFilm} name={props.projekcija.film}>{props.projekcija.film}</button></td>
            <td>{props.projekcija.datumVremePrikazivanja}<button style={{marginLeft: "15px", color: "blue"}} onClick={props.handleClick} name={props.projekcija.id}>Prikazi projekciju</button></td>
            <td>{props.projekcija.tipProjekcije}</td>
            <td>{props.projekcija.sala}</td>
            <td>{props.projekcija.cenaKarte}</td>
        </tr>
    )
}

export default PrikazProjekcija