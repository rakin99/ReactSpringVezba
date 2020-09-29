import React from "react"

function Film(props){
    const btnDelete = props.state.roles.includes("ROLE_ADMIN") &&   <button
                                                                        name={props.film.id} 
                                                                        onClick={props.deleteFilm}>
                                                                        Obrisi film
                                                                    </button>

    const btnEdit = props.state.roles.includes("ROLE_ADMIN") &&   <button 
                                                                    name={props.film.id} 
                                                                    onClick={props.editFilm}>
                                                                        Izmeni film
                                                                </button>
    return(
        <div id="idFilm">
            <p>Naziv: {props.film.naziv}</p>
            <p>Reziser: {props.film.reziser}</p>
            <p>Glumci: {props.film.glumci}</p>
            <p>Žanrovi: {props.film.zanrovi}</p>
            <p>Trajanje: {props.film.trajanje}</p>
            <p>Distributer: {props.film.distributer}</p>
            <p>Zemlja porekla: {props.film.zemljaPorekla}</p>
            <p>Godina proizvodnje: {props.film.godinaProizvodnje}</p>
            <p>Opis: {props.film.opis}</p>
            {btnEdit}
            {btnDelete}
            <button name="film" onClick={props.cancel}>Tabela</button>
        </div>
    )
}

export default Film