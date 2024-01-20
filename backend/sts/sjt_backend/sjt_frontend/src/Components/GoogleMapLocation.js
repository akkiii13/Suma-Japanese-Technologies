import GoogleMapReact from 'google-map-react';

const AnyReactComponent = ({ text }) => (
  <div style={{
    color: 'white', 
    background: 'grey',
    padding: '15px 10px',
    display: 'inline-flex',
    textAlign: 'center',
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: '100%',
    transform: 'translate(-50%, -50%)'
  }}>
    {text}
  </div>
);

const GoogleMapLocation = () => {

    const defaultProps = {
        center: {lat: 59.95, lng: 30.33},
        zoom: 11
    };

    return (
        <GoogleMapReact defaultCenter={this.props.center} defaultZoom={this.props.zoom} />
    )

}

export { GoogleMapLocation, AnyReactComponent };