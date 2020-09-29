import React from "react"

function FilterUsers(props){

    return(
        <thead>
            <tr>
                <th>Korisnicko ime</th>
                <th>Datum registracije</th>
                <th>Uloga</th>
            </tr>
            <tr>
                <td>
                    <input 
                        id="idUsername"
                        type="text" 
                        placeholder="Korisnicko ime..." 
                        onChange={props.filter}
                    />
                </td>
                <th></th>
                <td style={{textAlign: "center"}}>
                    <label>
                        <select id="idRole" onChange={props.filter}>
                            <option value=""></option>
                            <option value="ROLE_USER ">USER</option>
                            <option value="ROLE_ADMIN ROLE_USER ">ADMIN</option>
                        </select>
                    </label>
                </td>
            </tr>
        </thead>
    )

}

export default FilterUsers