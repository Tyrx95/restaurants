import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('login');
  this.route('show');
  this.route('restaurants');
  this.route('restaurant', { path: 'restaurant/:restaurant_id' });
  this.route('reservation');
  this.route('register');
  this.route('current-user');
  this.route('admin', function() {
    this.route('restaurants', function() {
      this.route('edit',{ path: '/edit/:id' },function() {
        this.route('tables');
        this.route('menu');
      });
      this.route('add', function() {
        this.route('menu');
        this.route('tables');
      });
    });
    this.route('locations', function() {
      this.route('add');
      this.route('edit',{ path: '/edit/:id'});
    });
    this.route('users', function() {
      this.route('add');
      this.route('edit',{ path: '/edit/:id'});
    });
    this.route('categories', function() {
      this.route('add');
      this.route('edit',{ path: '/edit/:id'});
    });

  });
});

export default Router;
