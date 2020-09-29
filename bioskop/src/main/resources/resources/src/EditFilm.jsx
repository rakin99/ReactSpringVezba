import React from "react"

function EditFilm(props){
    return(
        <div id="editFilm">
            <table>
                <tbody>
                <tr>
                    <td>Naziv:</td>
                    <td><input name="naziv" onChange={props.handleChange} type="text" value={props.film.naziv}/></td>
                </tr>
                <tr>
                    <td>Reziser:</td>
                    <td><input name="reziser" onChange={props.handleChange} type="text" value={props.film.reziser}/></td>
                </tr>
                <tr>
                    <td>Glumci:</td>
                    <td><textarea name="glumci" onChange={props.handleChange} value={props.film.glumci}></textarea></td>
                </tr>
                <tr>
                    <td>Zanrovi:</td>
                    <td><input name="zanrovi" onChange={props.handleChange} type="text" value={props.film.zanrovi}/></td>
                </tr>
                <tr>
                    <td>Trajanje:</td>
                    <td><input name="trajanje" onChange={props.handleChange} type="text" value={props.film.trajanje}/></td>
                </tr>
                <tr>
                    <td>Distributer:</td>
                    <td><input name="distributer" onChange={props.handleChange} type="text" value={props.film.distributer}/></td>
                </tr>
                <tr>
                    <td>Zemlja porekla:</td>
                    <td><input name="zemljaPorekla" onChange={props.handleChange} type="text" value={props.film.zemljaPorekla}/></td>
                </tr>
                <tr>
                    <td>Godina proizvodnje:</td>
                    <td><input name="godinaProizvodnje" onChange={props.handleChange} type="text" value={props.film.godinaProizvodnje}/></td>
                </tr>
                <tr>
                    <td>Opis</td>
                    <td><textarea name="opis" onChange={props.handleChange} value={props.film.opis}></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button onClick={props.cancel}>Odustani</button>
                        <button onClick={props.handleSubmit}>Sacuvaj</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    )
}

export default EditFilm