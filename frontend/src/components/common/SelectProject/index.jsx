import React, { useState } from 'react';
import styled from 'styled-components';
import { SlArrowDown } from 'react-icons/sl';

const SelectProject = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedProject, setSelectedProject] = useState('프로젝트 선택하기');
  const projects = ['프로젝트 A', '프로젝트 B', '프로젝트 C'];

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleSelect = (project) => {
    setSelectedProject(project);
    setIsOpen(false);
  };

  return (
    <Container>
      <Selected onClick={toggleDropdown}>
        {selectedProject}
        <ArrowIcon isOpen={isOpen} />
      </Selected>
      {isOpen && (
        <Dropdown>
          {projects.map((project, index) => (
            // eslint-disable-next-line react/no-array-index-key
            <Option key={index} onClick={() => handleSelect(project)}>
              {project}
            </Option>
          ))}
        </Dropdown>
      )}
    </Container>
  );
};

export default SelectProject;

const Container = styled.div`
  position: relative;
  width: 23vw;
  max-width: 300px;
  min-width: 150px;
  padding: 10px;
  border: 1px solid #e0e0e0;
  border-radius: 5px;
`;

const Selected = styled.div`
  font-size: 20px;
  font-family: 'PaperlogyBold';
  padding: 15px;
  color: #7a7a7a;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;

  @media (max-width: 768px) {
    font-size: 14px;
    padding: 12px;
  }

  @media (max-width: 480px) {
    font-size: 12px;
    padding: 10px;
  }
`;

const ArrowIcon = styled(SlArrowDown)`
  color: #666666;
  transition: transform 0.3s;
  transform: ${({ isOpen }) => (isOpen ? 'rotate(180deg)' : 'rotate(0deg)')};
`;

const Dropdown = styled.ul`
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  background-color: #ffffff;
  border: 1px solid #e0e0e0;
  border-radius: 5px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1;
  list-style-type: none;
  padding: 0;
  margin: 0;
`;

const Option = styled.li`
  padding: 10px;
  color: #666666;
  cursor: pointer;
  transition: background-color 0.2s;
  font-size: 18px;

  &:hover {
    background-color: #f0f0f0;
  }

  @media (max-width: 768px) {
    font-size: 16px;
    padding: 8px;
  }

  @media (max-width: 480px) {
    font-size: 14px;
    padding: 6px;
  }
`;
