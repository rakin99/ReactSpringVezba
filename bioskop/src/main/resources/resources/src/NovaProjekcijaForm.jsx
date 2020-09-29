import React from "react"

function NovaProjekcija(props){

    const opt=props.filmovi.map(f => <option key={f.id} value={f.id}>{f.naziv}</option>)
    const optSale=props.state.saleZaIzbor.map(s => <option key={s.id} value={s.id}>{s.naziv}</option>)
    const optTip=props.state.tipoviZaIzbor.map(p => <option key={p} value={p}>{p}</option>)

    return(
        <form onSubmit={props.handleSubmit}>
            <label>
                <select id="film1" onChange={props.handleChange} name="film">
                    <option value="">--Izaberi film--</option>
                    {opt}
                </select>
            </label>
            <br/>
            <label>
                <select id="tipProjekcije1" onChange={props.handleChange} name="tipProjekcije">
                    <option value="">--Izaberi tip--</option>
                    {optTip}
                </select>
            </label>
            <br/>
            <label>
                <select id="sala1" onChange={props.handleChange} name="sala">
                    <option value="">--Izaberi salu--</option>
                    {optSale}
                </select>
            </label>
            <br/>
            <input 
                id="prikazivanje1"
                onChange={props.handleChange} 
                type="datetime-local" 
                name="datumVremePrikazivanja" 
            />
            <br/>
            <input id="cena1" type="text" name={"cenaKarte"} placeholder="Cena karte..." onChange={props.handleChange}/>
            <br/>
            <button type="submit">Dodaj</button>
        </form>
    )
}

export default NovaProjekcija