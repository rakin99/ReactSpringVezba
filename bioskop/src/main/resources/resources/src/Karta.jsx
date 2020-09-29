import React from "react"

function Karta(props){
    const btn = props.users === true ? <button onClick={props.cancel}>Tabela</button>:<button onClick={props.cancel}>Moj profil</button>
    return(
        <div id="idKarta">
            <p><b>Film:</b> {props.projekcija.film}</p>
            <p><b>Vreme prikazivanja filma:</b> {props.projekcija.datumVremePrikazivanja}</p>
            <p><b>Tip projekcije:</b> {props.projekcija.tipProjekcije}</p>
            <p><b>Sala:</b> {props.projekcija.sala}</p>
            <p><b>Sediste:</b> {props.karta.sediste}</p>
            <p><b>Cena karte:</b> {props.projekcija.cenaKarte}</p>
            {btn}
        </div>
    )
}

export default Karta