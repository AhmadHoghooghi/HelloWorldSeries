
function createEditForm(categoryName, parentCategoryName) {
    var containerDiv = document.getElementById("edit-div-" + categoryName);
    //create form
    var newCategoryForm = document.createElement("form");
    newCategoryForm.setAttribute("action", "/edit-category/" + categoryName);
    newCategoryForm.setAttribute("method", "post");
    containerDiv.appendChild(newCategoryForm);

    // create form text box input
    var newCategoryTextBox = document.createElement("input");
    newCategoryTextBox.setAttribute("type", "text");
    newCategoryTextBox.setAttribute("name", "new-category-name");
    newCategoryTextBox.setAttribute("value", categoryName);
    newCategoryForm.appendChild(newCategoryTextBox);

    // create form text box input
    var newCategoryParentInput = document.createElement("input");
    newCategoryParentInput.setAttribute("type", "hidden");
    newCategoryParentInput.setAttribute("name", "parent-category-name");
    newCategoryParentInput.setAttribute("value", parentCategoryName);
    newCategoryForm.appendChild(newCategoryParentInput);

    //submit button
    var newCategorySubmitButton = document.createElement("input");
    newCategorySubmitButton.setAttribute("type", "submit");
    newCategorySubmitButton.setAttribute("value", "Apply");
    newCategoryForm.appendChild(newCategorySubmitButton);

    newCategoryForm.style.display = "inline";
    document.getElementById("edit-button-" + categoryName).disabled = true;
    document.getElementById("category-link-" + categoryName).style.display = "none";

    var editDivs = document.getElementsByClassName("edit-div");
    for (var i = 0; i < editDivs.length; i++) {
        var editDiv = editDivs[i];
        if (editDiv.id != ("edit-div-" + categoryName)) {
            editDiv.innerHTML = "";
        }
    }

    var editButtons = document.getElementsByClassName("edit-button");
    for (var i = 0; i < editButtons.length; i++) {
        var editButton = editButtons[i];
        if (editButton.id != ("edit-button-" + categoryName)) {
            editButton.disabled = false;
        }
    }

    var categoryLinks = document.getElementsByClassName("category-link");
    for (var i = 0; i < categoryLinks.length; i++) {
        var categoryLink = categoryLinks[i];
        if (categoryLink.id != ("category-link-" + categoryName)) {
            categoryLink.style.display = "inline";
        }
    }
}

function editItem(categoryName) {
    var editButtonId = "edit-button-" + categoryName;
    var editFormId = "edit-form-" + categoryName;
    var categoryLinkId = "category-link-" + categoryName;
    document.getElementById(editButtonId).disabled = true;
    document.getElementById(editFormId).style.display = "inline";
    document.getElementById(categoryLinkId).style.display = "none";
}