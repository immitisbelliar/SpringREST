const form_del = document.getElementById('formForDeleting');
const id_del = document.getElementById('id_del');
const firstName_del = document.getElementById('First name_del');
const lastName_del = document.getElementById('Last name_del');
const age_del = document.getElementById('age_del');
const username_del = document.getElementById('email_del');

async function deleteModalData(id) {
    const urlForDel = '/rest/admin/users/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                firstName_del.value = `${user.firstName}`;
                lastName_del.value = `${user.lastName}`;
                age_del.value = `${user.age}`;
                username_del.value = `${user.username}`;
            })
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}

function deleteUser() {

    form_del.addEventListener('submit', deletingUser);

    function deletingUser(event) {
        event.preventDefault();
        let url = '/rest/admin/users/' + id_del.value

        let method = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json"
            }
        }

        fetch(url, method).then(() => {
            $("#deleteCloseBtn").click();
            getAdminGeneralPage();
        });
    }
}