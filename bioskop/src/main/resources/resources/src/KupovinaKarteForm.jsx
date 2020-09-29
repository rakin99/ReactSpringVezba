import React from "react"

function KupovinaKarteForm(props){
    const selectSedista=props.state.slobodnaSedista.map(s => <option key={s.redniBroj} value={s.redniBroj}>{s.redniBroj}</option>)
    const izabranaSedista=props.state.izabranaSedista.map(s => <option key={s.redniBroj} value={s.redniBroj}>{s.redniBroj}</option>)
    return(
        <form onSubmit={props.handleSubmit}>
            <p>Film: {props.state.projekcija.film}</p>
            <p>Vreme prikazivanje: {props.state.projekcija.datumVremePrikazivanja}</p>
            <p>Tip projekcije: {props.state.projekcija.tipProjekcije}</p>
            <p>Sala: {props.state.projekcija.sala}</p>
            <p>Cena karte: {props.state.projekcija.cenaKarte}</p>
            <label>
                Izaberite sediste:
                <select id="slobodnaSedista">
                    <option value=""></option>
                    {selectSedista}
                </select>
            </label>
            <button type="button" onClick={props.dodajSediste}>Dodaj sediste</button>
            <br/>
            <label>
                Izabrana sedista:
                <select id="izabranaSedista">
                    <option value=""></option>
                    {izabranaSedista}
                </select>
            </label>
            <button type="button" onClick={props.ukloniSediste}>Ukloni sediste</button>
            <p>Ukupna cena: {props.state.izabranaSedista.length * props.state.projekcija.cenaKarte}</p>
            <br/>
            <br/>
            <button name="kupovina" type="submit">Kupi kartu</button>
        </form>
    )
}

export default KupovinaKarteForm