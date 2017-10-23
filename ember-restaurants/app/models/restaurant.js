import DS from 'ember-data';

export default DS.Model.extend({
  restaurantName: DS.attr(),
  description: DS.attr(),
  latitude: DS.attr(),
  longitude: DS.attr(),
  mark: DS.attr(),
  votes: DS.attr(),
  priceRange: DS.attr(),
  imageFileName: DS.attr(),
  coverFileName: DS.attr(),
  location: DS.attr(),
  foodType: DS.attr()
});
