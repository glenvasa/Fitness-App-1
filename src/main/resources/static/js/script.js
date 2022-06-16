//if(window.location.pathname === "/") {
//window.addEventListener("load", () => {
//    console.log(window.location.pathname)
//    retrieveAgeHeight();
//})


// only want "load" event listener to be added and call addServingsCalories if /servings route
if(window.location.pathname === "/servings") {
window.addEventListener("load", () => {
    console.log(window.location.pathname)
    addServingsCalories();
})

}

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

// Give user option to click button and auto input current date/time for Meal
const currentDateTime = () => {
  const month = document.getElementById('month')
  const day = document.getElementById('day')
  const year = document.getElementById('year')
  const hour = document.getElementById('hour')
  const minute = document.getElementById('minute')
  const dayNight = document.getElementById('dayNight')

  const currentDate = new Date();
  const cDay = currentDate.getDate()
  const cMonth = currentDate.getMonth() + 1
  const cYear = currentDate.getFullYear()
  let cHours = currentDate.getHours()
  let cMinutes = currentDate.getMinutes()
  let cDayNight;

  cHours >= 12 ? cDayNight = "PM" : cDayNight = "AM"
  cHours > 12 ? cHours -= 12 : cHours = cHours
  cMinutes <= 9 ? cMinutes = "0" + cMinutes : cMinutes = cMinutes

  month.value = cMonth;
  day.value = cDay;
  year.value = cYear;
  hour.value = cHours;
  minute.value = cMinutes;
  dayNight.value = cDayNight;

}

// give User option to click a button and
const currentDate = () => {
  const month = document.getElementById('month')
  const day = document.getElementById('day')
  const year = document.getElementById('year')


  const currentDate = new Date();
  const cDay = currentDate.getDate()
  const cMonth = currentDate.getMonth() + 1
  const cYear = currentDate.getFullYear()

  month.value = cMonth;
  day.value = cDay;
  year.value = cYear;

}



const calorieCalculator = () => {
  const weight = document.getElementById('calcWeight').value
  const height = document.getElementById('calcHeight').value
  const age = document.getElementById('calcAge').value
  const exerciseLevel = document.getElementById('exerciseLevel').value
  const male= document.getElementById('male').checked
  const female= document.getElementById('female').checked

  const maintenanceCalories = document.getElementById('maintenanceCalories')
  let calories;

  if(male){
    calories = exerciseLevel * (66 + (6.23 * weight) + (12.7 * height) - (6.8 * age)) - 120
    console.log("male")
  } else if(female){
    calories = exerciseLevel * (655 + (4.35 * weight) + (4.7 * height) - (4.7 * age)) - 120
      console.log("female")
    } else {
     alert("Please choose gender for Calorie Calculator")
     calories = 0
    }

console.log(exerciseLevel)

  maintenanceCalories.value = Math.round(calories)

}


//const saveMaintCals = () => {
//   const maintCals = document.getElementById("maintCals").value
//   console.log(maintCals)
//   fetch(`/user/update/${maintCals}`, {
//         method: 'POST'})
//
//}



// gives user option to delete a created set before saving a workout
//const deleteSet = async (setId) => {
//      await fetch(`/sets/delete/${setId}`, {
//      method: 'DELETE'})
//}



// opens Meal Modal when mealDate-button clicked
const moveMealModal = (event) => {
event.preventDefault()
    const mealDateButtons = document.querySelectorAll('.mealDate')
    const mealModal = document.getElementById('mealModal')
    const mealModalContent = document.getElementById("mealModalContent")
    const closeModalButton = document.getElementById('closeMealModal')

mealModal.style.top = 0;

//    mealModal.classList.remove('invisible')

    closeModalButton.onclick = function() {
//        mealModal.classList.add('invisible')
//        mealModalContent.innerText = ""
    }

    // only want window onclick to function IF model is open; otherwise it will prevent other page button onclick fns
    window.onclick = function(e) {
          if (e.target == mealModal && !mealModal.classList.contains('invisible')) {

//            mealModal.classList.add('invisible')
//            mealModalContent.innerText = ""
            window.onclick = null;
          }
    }

//   console.log(event.target.innerText)

//    fetchMealData(event.target.innerText)


}

// opens Workout Modal when workoutDate-button clicked
const openWorkoutModal = () => {

    const workoutDateButtons = document.querySelectorAll('.workoutDate')
    const workoutModal = document.getElementById('workoutModal')
    const closeModalButton = document.getElementById('closeWorkoutModal')

    workoutModal.classList.remove('hidden')

    closeModalButton.onclick = () => workoutModal.classList.add('hidden')

    // only want window onclick to function IF model is open; otherwise it will prevent other page button onclick fns
    window.onclick = function(e) {
          if (e.target == workoutModal && !workoutModal.classList.contains('hidden')) {
            workoutModal.classList.add('hidden')
            window.onclick = null;
          }
    }

fetchWorkoutData(event.target.innerText)

}


//if(window.location.pathname == "/profile") {
//window.addEventListener("load", () => {
//    const mealModal = document.getElementById('mealModal')
//    mealModal.classList.add('invisible')
//})




const fetchMealData = (event) => {

window.location.reload()
   const date = event.target.innerText
//   console.log(date)
   fetch(`/profile/meals/${date}`)

//      .catch(err => {console.log(err)})
//mealModal.classList.add('invisible')
//moveMealModal(event)

}

const fetchWorkoutData = (date) => {
   console.log(date)
   fetch(`/profile/workouts/${date}`)
      .then(res => { console.log(res)})
      .catch(err => {console.log(err)})
}



const modalUp = (event) => {
//  event.preventDefault()

const mealModal = document.getElementById('mealModal')
  mealModal.style.top = 0;
  console.log('timeout')


}