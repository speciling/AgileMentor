import React, { useState } from 'react';
import styled from 'styled-components';
import { SlArrowDown } from 'react-icons/sl';
import { useProjects } from '../../../provider/projectContext';

const SelectProject = () => {
  const [isOpen, setIsOpen] = useState(false);

  const { projects, selectedProjectId, setSelectedProjectId } = useProjects();

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleSelect = (project) => {
    setSelectedProjectId(project.projectId);
    setIsOpen(false);
  };

  const selectedProject =
    projects.find((project) => project.projectId === selectedProjectId)
      ?.title || '프로젝트 선택하기';

  return (
    <Container>
      <Selected onClick={toggleDropdown}>
        {selectedProject}
        <ArrowIcon isOpen={isOpen} />
      </Selected>
      {isOpen && (
        <Dropdown>
          {projects.map((project) => (
            <Option
              key={project.projectId}
              onClick={() => handleSelect(project)}
            >
              {project.title}
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
  width: 20vw;
  padding: 1vh;
  border: 0.1vw solid #e0e0e0;
  border-radius: 0.5vw;
`;

const Selected = styled.div`
  font-size: 1.8vh;
  font-family: 'PaperlogyBold';
  padding: 1.5vh;
  color: #7a7a7a;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;

  @media (max-width: 768px) {
    font-size: 1.4vh;
    padding: 1.2vh;
  }

  @media (max-width: 480px) {
    font-size: 1.2vh;
    padding: 1vh;
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
  border: 0.1vw solid #e0e0e0;
  border-radius: 0.5vw;
  box-shadow: 0 0.4vh 0.8vh rgba(0, 0, 0, 0.1);
  z-index: 1;
  list-style-type: none;
  padding: 0;
  margin: 0;
`;

const Option = styled.li`
  padding: 1vh;
  color: #666666;
  cursor: pointer;
  transition: background-color 0.2s;
  font-size: 1.8vh;

  &:hover {
    background-color: #f0f0f0;
  }

  @media (max-width: 768px) {
    font-size: 1.6vh;
    padding: 0.8vh;
  }

  @media (max-width: 480px) {
    font-size: 1.4vh;
    padding: 0.6vh;
  }
`;
