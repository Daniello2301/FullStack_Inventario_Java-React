
export function InputGroup(props){
    return(
        <>
        <div class="mb-3">
            <label for={props.name} class="form-label">Email address</label>
            <input type="email" class="form-control" id={props.name} placeholder={props.name} />
        </div>
        </>
    )
}