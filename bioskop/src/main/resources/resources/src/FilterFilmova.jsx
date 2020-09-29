import React from "react"

function FilterFilmova(props){
    return(
        <thead>
            <tr>
                <th>
                    Naziv
                </th>
                <th>
                    Zanr
                </th>
                <th>
                    Trajanje
                </th>
                <th>
                    Distributer
                </th>
                <th>
                    Zemlja porekla
                </th>
                <th>
                    Godina proizvodnje
                </th>
            </tr>
            <tr>
                <th>
                    <input 
                        id="idNaziv"
                        type="text" 
                        placeholder="Naziv..." 
                        onChange={props.filter}
                    />
                </th>
                <th>
                    <input 
                        id="idZanr"
                        type="text" 
                        placeholder="Zanr..." 
                        onChange={props.filter}
                    />
                </th>
                <th>
                    <input 
                        id="idOd"
                        type="text" 
                        placeholder="Od..." 
                        onChange={props.filter}
                    />
                    <br/>
                    <input 
                        id="idDo"
                        type="text" 
                        placeholder="Do..." 
                        onChange={props.filter}
                    />
                </th>
                <th>
                    <input 
                        id="idDist"
                        type="text" 
                        placeholder="Distributer..." 
                        onChange={props.filter}
                    />
                </th>
                <th>
                    <input 
                        id="idZemlja"
                        type="text" 
                        placeholder="Zemlja porekla..." 
                        onChange={props.filter}
                    />
                </th>
                <th>
                    <input 
                        id="idGodOd"
                        type="text" 
                        placeholder="Od..." 
                        onChange={props.filter}
                    />
                    <br/>
                    <input 
                        id="idGodDo"
                        type="text" 
                        placeholder="Do..." 
                        onChange={props.filter}
                    />
                </th>
            </tr>
        </thead>
    )
}

export default FilterFilmova