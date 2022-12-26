const url = '/rest/admin/personalPage'

async function getUserPage() {
    let page = await fetch(url)

    if (page.ok) {
        let user = await page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`);
    }
}

function getInformationAboutUser(user) {
    let tr = document.createElement("tr")
    let roles = []

    for (let role of user.roles) {
        roles.push(" " + role.roleName.toString().replaceAll('ROLE_', ''))
    }

    tr.innerHTML =
        `<tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.username}</td>
            <td>${roles}</td>
        </tr>`
    document.getElementById(`tbody`).append(tr);
}

getUserPage();