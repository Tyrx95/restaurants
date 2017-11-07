import Ember from 'ember';
import Reservation from 'ember-restaurants/models/reservation';
import Comment from 'ember-restaurants/models/comment';

export default Ember.Controller.extend({
  session: Ember.inject.service(),
  reservationService: Ember.inject.service(),
  restaurantService: Ember.inject.service(),
  hasAvailable: false,
  availableTimes: [],
  hasError: false,
  error: "",
  numOfTables: 0,
  reservation: Reservation.create({
    persons: "1 people",
    date: new Date(),
    hour: "11:00 AM"
  }),
  comment: Comment.create({
    mark: 0,
    comment:""
  }),
  clear: function() {
        this.setProperties({
            availableTimes: [],
            hasAvailable: false,
            hasError: false,
            error: "",
            numOfTables: 0
        });
    }.observes('model'),
  actions: {
    findTable: function() {
      var restaurantId = this.get('model.id');
      var reservation = this.get('reservation');
      this.get('reservationService').checkReservationAvailability(restaurantId, reservation).then(function(result) {
        this.set('numOfTables', result.tablesLeft);
        console.log(result);
        if(result.isAvailable){
          var time = this.get('reservation.hour');
          this.set('reservationTime',time);
          this.transitionToRoute('reservation');
        }
          this.set('availableTimes', result.bestTime);
          this.set('hasError', false);
          this.set('hasAvailable',true);
      }.bind(this), function(data) {
          this.set('error',"No tables available, please try some other time.");
          this.set('hasError',true);
          alert(this.get('error'));
      }.bind(this));
    },

    reserveTable: function(time){
        this.set('reservationTime',time);
        this.transitionToRoute('reservation');
    },

    rateClicked: function(params) {
        console.log(params);
        this.set('comment.mark', params.rating);
    },

    rate: function(){
       var idRestaurant = this.get('model.id');
       var idUser = this.get('session.data.authenticated.id');
       var mark = this.get('comment.mark');
       var comment = this.get('comment.comment');

       this.get('restaurantService').insertComment(idRestaurant, idUser, mark, comment).then(function(data) {
                console.log("success:"+ data);
                this.send('afterRate');
            }.bind(this), function() {
                console.log("Error while rating.");
            });
    }


  }
});
