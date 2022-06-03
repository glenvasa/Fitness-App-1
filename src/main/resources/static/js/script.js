//const displaySaveWorkout = () => {
//    console.log("Save Workout")
//    const grid = document.getElementById('setsGrid')
//    const addForm = document.getElementById('addSetForm')
//    const saveForm = document.getElementById('saveWorkoutForm')
//    grid.style.display = 'none'
//    addForm.style.display = 'none'
//    saveForm.style.display = 'block'
//}

window.addEventListener("load", () => {
    addServingsCalories();

})

// Add up Calories for each Serving on Meal Card and set = Total Meal Calories
const addServingsCalories = () => {
    const servingsCals = document.querySelectorAll('#totalCals')
    console.log(servingsCals)
    const mealCals = document.getElementById('mealCals')
    let totalMealCals = 0
    servingsCals.forEach(s => totalMealCals += Number(s.innerText));
   console.log(totalMealCals)
   mealCals.value = totalMealCals
}