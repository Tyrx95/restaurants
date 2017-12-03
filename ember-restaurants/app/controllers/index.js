import Ember from 'ember';

export default Ember.Controller.extend({
  session:Ember.inject.service(),

  timeChoices: ["12:00 AM", "12:30 AM", "01:00 AM", "01:30 AM", "02:00 AM", "02:30 AM", "03:00 AM", "03:30 AM", "04:00 AM",
                  "04:30 AM", "05:00 AM", "05:30 AM", "06:00 AM", "06:30 AM", "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM",
                  "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM",
                  "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM",
                  "06:00 PM", "06:30 PM", "07:00 PM", "07:30 PM", "08:00 PM", "08:30 PM", "09:00 PM", "09:30 PM", "10:00 PM",
                  "10:30 PM", "11:00 PM", "11:30 PM"],
  minDate: new Date(),
  maxDate: new Date(),
  personChoices: ["1 people", "2 people", "3 people","4 people", "5 people", "6 people","7 people", "8 people"],
  searchValue: "",
  persons:[],
  date: new Date(),
  time:"",


  init: function(){
    this._super();
    this.set('minDate', new Date());
    var thisDay = new Date();
    thisDay.setMonth(thisDay.getMonth()+1);
    this.set('maxDate', thisDay);
  },
  clear: function() {
        this.setProperties({
            persons: [],
            searchValue: "",
            date: new Date(),
            time: ""
        });
    }.observes('locations'),

  actions: {
    findTable: function(){
      console.log(this.get('searchValue'));
      this.transitionToRoute('/restaurants');
    }
  }


});
