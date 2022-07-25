import React from 'react';

const FooterComponent = () => {
    return (
        <div style={{
            position: "absolute",
            height: '60px',
            lineHeight: '60px',
            backgroundColor: 'white',
            bottom: '20px',
            right: '20px'
        }}>
            <footer className = "footer" >
                <span className="text-muted">Made by Varodi Andrei-Constantin</span>
            </footer>
        </div>
    );
};

export default FooterComponent;