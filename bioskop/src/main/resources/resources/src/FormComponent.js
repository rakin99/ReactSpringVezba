import React from "react"

function FormComponent(props){
    return(
        <main>
            <form>
                <input 
                    type="text" 
                    placeholder="First Name"   
                    name="firstName"
                    onChange={props.handleChange}
                    value={props.state.firstName}
                /> <br/>   
                <input 
                    type="text" 
                    placeholder="Last Name" 
                    name="lastName" 
                    onChange={props.handleChange}
                    value={props.state.lastName}
                /> <br/>
                <input 
                    type="text" 
                    placeholder="Age" 
                    name="age" 
                    onChange={props.handleChange}
                    value={props.state.age}
                /> <br/>

                <label> Gender: <br/>
                    <input 
                        onChange={props.handleChange} 
                        type="radio" 
                        value="male" 
                        name="gender" 
                    />Male
                </label> 
                <br/>
                <label>
                    <input 
                        onChange={props.handleChange} 
                        type="radio" 
                        value="female" 
                        name="gender" 
                    /> Female
                </label>
                <br/>
                
                <label>
                    Select your destination:
                    <br/>
                    <select onChange={props.handleChange} name="destination" 
                    value={props.state.destination}>
                        <option value="">--Select your destination--</option>
                        <option value="budapest">Budapest</option>
                        <option value="belgrade">Belgrade</option>
                        <option value="new york">New York</option>
                    </select>
                </label>

                <br/>
                <label>
                    Is vegan:
                    <input 
                        onChange={props.handleChange} 
                        type="checkbox" 
                        name="isVegan"
                        checked={props.state.isVegan}/>
                    <br/>
                    Is kosher:
                    <input 
                        onChange={props.handleChange} 
                        type="checkbox" 
                        name="isKosher"
                        checked={props.state.isKosher}/>
                    <br/>
                    Is lactose free:
                    <input 
                        onChange={props.handleChange} 
                        type="checkbox" 
                        name="isLactoseFree"
                        checked={props.state.isLactoseFree}/>
                    <br/>
                </label>

                <button>Submit</button>
            </form>
            <hr/>
            <h1>Entered information:</h1>
            <p>Your name: {props.state.firstName} {props.state.lastName}</p>
            <p>Your age: {props.state.age}</p>
            <p>Your gender: {props.state.gender}</p>
            <p>Your destination: {props.state.destination}</p>
            <p>Is a vegan: {props.state.isVegan ? "Yes":"No"}</p>
            <p>Is a kosher: {props.state.isKosher ? "Yes":"No"}</p>
            <p>Is a lactose free: {props.state.isLactoseFree ? "Yes":"No"}</p>
        </main>
    )
}

export default FormComponent