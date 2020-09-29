import React from "react"

function FilmForm (props){
    return(
        <form onSubmit={props.handleSubmit}>
                    <input placeholder="Naziv filma..." name="naziv" type="text" onChange={props.handleChange} value={props.state.naziv} />
                    <br/>
                    <input placeholder="Reziser filma..." name="reziser" type="text" onChange={props.handleChange} value={props.state.reziser} />
                    <br/>
                    <textarea placeholder="Glumci filma..." name="glumci" onChange={props.handleChange} value={props.state.glumci}></textarea>
                    <br/>
                    <input placeholder="Zanr filma..." name="zanrovi" type="text" onChange={props.handleChange} value={props.state.zanrovi} />
                    <br/>
                    <input placeholder="Trajanje filma..." name="trajanje" type="text" onChange={props.handleChange} value={props.state.trajanje} />
                    <br/>
                    <input placeholder="Distributer filma..." name="distributer" type="text" onChange={props.handleChange} value={props.state.distributer} />
                    <br/>
                    <input placeholder="Zemlja porekla filma..." name="zemljaPorekla" type="text" onChange={props.handleChange} value={props.state.zemljaPorekla} />
                    <br/>
                    <input placeholder="Godina proizvodnje filma..." name="godinaProizvodnje" type="text" onChange={props.handleChange} value={props.state.godinaProizvodnje} />
                    <br/>
                    <textarea placeholder="Opis filma..." name="opis" onChange={props.handleChange} value={props.state.opis}></textarea>
                    <br/>
                    <button type="submit">Dodaj film</button>
                </form>
    )
}

export default FilmForm