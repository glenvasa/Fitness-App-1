const displaySaveWorkout = () => {
    console.log("Save Workout")
    const grid = document.getElementById('setsGrid')
    const addForm = document.getElementById('addSetForm')
    const saveForm = document.getElementById('saveWorkoutForm')
    grid.style.display = 'none'
    addForm.style.display = 'none'
    saveForm.style.display = 'block'
}