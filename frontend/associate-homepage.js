window.addEventListener('load', async () => {

    let res = await fetch('http://localhost:8080/checkloginstatus', {
        credentials: 'include',
        method: 'GET'
    });

    if (res.status === 200) {
        let userObj = await res.json();

        if (userObj.userRole === 'trainer') {
            window.location.href = 'trainer-homepage.html';
        }
    } else if (res.status === 401) {
        window.location.href = 'index.html';
    }

    populateTableWithAssignments();
});


async function populateTableWithAssignments() {
    let res = await fetch('http://localhost:8080/assignments', {
        credentials: 'include',
        method: 'GET'
    });

    let tbodyElement = document.querySelector("#assignment-table tbody");
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

        if (assignment.graderId != 0) {
            td3.innerHTML = assignment.grade;
            td4.innerHTML = assignment.graderId;
        } else {
            td3.innerHTML = 'Not graded';
            td4.innerHTML = '-';
        }
 

        let td5 = document.createElement('td');
        td5.innerHTML = assignment.authorId;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);

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