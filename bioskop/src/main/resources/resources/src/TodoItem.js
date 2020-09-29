import React from "react"

function TodoItem(props){
    const styles={
      color: "gray",
      fontStyle: "italic",
      textDecoration: "line-through"
    }

    return(
    <div className="todo-item">

      <input type="checkbox" onChange={() => props.handleChange(props.it.id)} checked={props.it.completed}/>
      <p style={props.it.completed ? styles:null}>{props.it.text}</p> 

    </div>
    )
}

export default TodoItem