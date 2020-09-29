import React from "react"

function Projekcija(props){
    const btnDelete = props.state.roles.includes("ROLE_ADMIN") &&   <button
                                                                        name={props.projekcija.id} 
                                                                        onClick={props.deleteProjekcija}>
                                                                        Obrisi projekciju
                                                                    </button>

    const btnKupovina = props.state.roles.includes("ROLE_USER") &&   <button
                                                                        name={props.projekcija.id} 
                                                                        onClick={props.kupiKartu}>
                                                                        Kupi kartu
                                                                    </button>
    return(
        <div id="idProjekcija">
            <p>Film: {props.projekcija.film}</p>
            <p>Vreme prikazivanja: {props.projekcija.datumVremePrikazivanja}</p>
            <p>Tip projekcije: {props.projekcija.tipProjekcije}</p>
            <p>Sala: {props.projekcija.sala}</p>
            <p>Cena karte: {props.projekcija.cenaKarte}</p>
            <p>Broj slobodnih mesta: {props.brMesta.length}</p>
            {btnDelete}
            {btnKupovina}
            <button name="projekcija" onClick={props.cancel}>Tabela</button>
        </div>
    )
}

export default Projekcija