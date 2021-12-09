// Check to see if the user is logged in or not, and if not, relocate them back to
// login screen
window.addEventListener('load', async () => {

    let res = await fetch('http://localhost:8080/checkloginstatus', {
        credentials: 'include',
        method: 'GET'
    });

    if (res.status === 200) {
        let userObj = await res.json();

        if (userObj.userRole === 'associate') {
            window.location.href = 'associate-homepage.html';
        }
    } else if (res.status === 401) {
        window.location.href = 'index.html';
    }

    // If we make it past the authorization checks, call another function that will 
    // retrieve all assignments
    populateTableWithAssignments();

});

async function populateTableWithAssignments() {
    let res = await fetch('http://localhost:8080/assignments', {
        credentials: 'include',
        method: 'GET'
    });

    let tbodyElement = document.querySelector("#assignment-table tbody");
    tbodyElement.innerHTML = '';

    let assignmentArray =  await res.json();

    for (let i = 0; i < assignmentArray.length; i++) {
        let assignment = assignmentArray[i];

        let tr = document.createElement('tr');

        let td1 = document.createElement('td');
        td1.innerHTML = assignment.id;

        let td2 = document.createElement('td');
        td2.innerHTML = assignment.assignmentName;

        let td3 = document.createElement('td'); // grade
        let td4 = document.createElement('td'); // grader id

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.authorId;

        // If the assignment has already been graded, display the grade and graderId
        let td6 = document.createElement('td'); // grade button
        let td7 = document.createElement('td'); // grade input
        let td8 = document.createElement('td'); // view image button

        let viewImageButton = document.createElement('button');
        viewImageButton.innerHTML = 'View Image';
        td8.appendChild(viewImageButton);

        viewImageButton.addEventListener('click', () => {
            // Add the is-active class to the modal (so that the modal appears)
            // inside of the modal on div.modal-content (div w/ class modal-content)
            //  -> img tag with src="http://localhost:8080/assignments/{id}/image"
            let assignmentImageModal = document.querySelector('#assignment-image-modal');

            // Close button
            let modalCloseElement = assignmentImageModal.querySelector('button');
            modalCloseElement.addEventListener('click', () => {
                assignmentImageModal.classList.remove('is-active');
            });

            // you can take an element and use querySelector on it to find the child elements
            // that are nested within it
            let modalContentElement = assignmentImageModal.querySelector('.modal-content');
            modalContentElement.innerHTML = '';

            let imageElement = document.createElement('img');
            imageElement.setAttribute('src', `http://localhost:8080/assignments/${assignment.id}/image`);
            modalContentElement.appendChild(imageElement);

            assignmentImageModal.classList.add('is-active'); // add a class to the modal element to have it display
        })

        if (assignment.graderId != 0) {
            td3.innerHTML = assignment.grade;
            td4.innerHTML = assignment.graderId;
        } else { // otherwise, display the below content
            td3.innerHTML = 'Not graded';
            td4.innerHTML = '-';

            // Main challenge here is linking each button with a particular parameter
            // (assignment id that we want to change the grade of)
            let gradeInput = document.createElement('input');
            gradeInput.setAttribute('type', 'number');

            let gradeButton = document.createElement('button');
            gradeButton.innerText = 'Grade Me';
            gradeButton.addEventListener('click', async () => {
               
                let res = await fetch(`http://localhost:8080/assignments/${assignment.id}/grade`, 
                    {
                        credentials: 'include',
                        method: 'PATCH',
                        body: JSON.stringify({
                            grade: gradeInput.value
                        })
                    });

                if (res.status === 200) {
                    populateTableWithAssignments(); // Calling the function to repopulate the entire
                    // table again
                }
            });

            td6.appendChild(gradeButton);
            td7.appendChild(gradeInput);
        }
 
        let td9 = document.createElement('td'); // a first name
        td9.innerHTML = assignment.authorfirstName;

        let td10 = document.createElement('td'); // a last name
        td10.innerHTML = assignment.authorlastName;

        let td11 = document.createElement('td'); // g first name
        td11.innerHTML = assignment.graderFirstName;

        let td12 = document.createElement('td'); // g last name
        td12.innerHTML = assignment.graderLastName;
        

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tr.appendChild(td11);
        tr.appendChild(td12);

        tbodyElement.appendChild(tr);
    }
}


let logoutBtn = document.querySelector('#logout');

logoutBtn.addEventListener('click', async () => {

    let res = await fetch('http://localhost:8080/logout', {
        'method': 'POST',
        'credentials': 'include'
    });

    if (res.status === 200) {
        window.location.href = 'index.html';
    }

})